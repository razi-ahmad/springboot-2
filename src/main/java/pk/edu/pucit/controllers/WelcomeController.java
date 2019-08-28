/**
 * 
 */
package pk.edu.pucit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Razi Ahmad
 *
 */
@RestController
public class WelcomeController {

	@Autowired
	private ResourceBundleMessageSource messageSource;

	@GetMapping(value = "/v1/api/welcome")
	public String getMessageInI18NFormat() {
		return messageSource.getMessage("label.welcome", null, LocaleContextHolder.getLocale());
	}
}
