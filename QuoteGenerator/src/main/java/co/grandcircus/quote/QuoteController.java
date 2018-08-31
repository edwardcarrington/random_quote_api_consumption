package co.grandcircus.quote;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import co.grandcircus.quote.model.Quote;

@Controller
public class QuoteController {

	@RequestMapping("/")
	public ModelAndView quote() {
		ModelAndView mav = new ModelAndView("index");

		// Create a rest template
		RestTemplate restTemplate = new RestTemplate(); // simplify method of talking to REST services

		// Set up headers.
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.USER_AGENT, "Let me in!"); // Most APIs need to have headers

		// Set url
		String url = "https://gturnquist-quoters.cfapps.io/api/random";

		// Make the Request.
		ResponseEntity<Quote> response = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers),
				Quote.class);

		// Extract body from response. must do this step when using "exchange"
		Quote result = response.getBody();

		// Drill into what is needed
		mav.addObject("quote", result.getValue().getQuote());

		return mav;

	}

}
