package packag.com.restController;

import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import packag.com.domain.Message;

@RestController
public class HomeRestController {
	@RequestMapping("/rest")
	public ResponseEntity<Message> getData(){
		Message m = new Message("evo, nije lose");
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		m.setDateString(sdf.format(m.getDate()));
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
}
