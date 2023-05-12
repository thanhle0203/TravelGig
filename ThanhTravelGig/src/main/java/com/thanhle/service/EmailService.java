package com.thanhle.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.thanhle.dto.EmailDetails;

public interface EmailService {

	public String sendSimpleMail(EmailDetails details);
	public String sendMailWithAttachment(EmailDetails details);

	
}