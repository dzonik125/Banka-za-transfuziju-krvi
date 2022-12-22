package group8.bloodbank.controller;

import group8.bloodbank.model.DTO.JwtAuthenticationRequest;
import group8.bloodbank.model.DTO.UserDTO;
import group8.bloodbank.model.DTO.UserTokenState;
import group8.bloodbank.model.Donor;
import group8.bloodbank.model.User;
import group8.bloodbank.service.interfaces.DonorService;
import group8.bloodbank.service.interfaces.UserService;
import group8.bloodbank.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

	@Autowired
	private TokenUtils tokenUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	@Autowired
	private DonorService donorService;
	
	// Prvi endpoint koji pogadja korisnik kada se loguje.
	// Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
	@PostMapping("/login")
	public ResponseEntity<UserTokenState> createAuthenticationToken(
			@RequestBody JwtAuthenticationRequest authenticationRequest, HttpServletResponse response) {
		// Ukoliko kredencijali nisu ispravni, logovanje nece biti uspesno, desice se
		// AuthenticationException
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		// Ukoliko je autentifikacija uspesna, ubaci korisnika u trenutni security
		// kontekst
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Kreiraj token za tog korisnika
		User user = (User) authentication.getPrincipal();
		String jwt = tokenUtils.generateToken(user);
		int expiresIn = tokenUtils.getExpiredIn();

		// Vrati token kao odgovor na uspesnu autentifikaciju
		return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
	}

	// Endpoint za registraciju novog korisnika
	@PostMapping("/signup")
	public ResponseEntity<User> addUser(@RequestBody UserDTO user, UriComponentsBuilder ucBuilder) throws MessagingException, UnsupportedEncodingException {

		if (this.userService.findByUsername(user.getEmail()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		Donor donor = this.donorService.registerDonor(user);

		if(donor == null){
			return new ResponseEntity<>(donor, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>(donor, HttpStatus.CREATED);
	}

	@GetMapping("/verify")
	public ResponseEntity<Boolean> verify(@Param("code")String code, HttpServletResponse res){
		try {
			if (donorService.verify(code)) {
				res.sendRedirect("http://localhost:4200/auth/login");
				return new ResponseEntity<>(true, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(false, HttpStatus.CONFLICT);
			}
		} catch(IOException e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}


}