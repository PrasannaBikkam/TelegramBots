package com.dbs.c2e.ecosystem.telegram.bot.handler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class UserInputBot extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			
			// Set variables
	        String user_first_name = update.getMessage().getChat().getFirstName();
	        String user_last_name = update.getMessage().getChat().getLastName();
	        String user_username = update.getMessage().getChat().getUserName();
	        long user_id = update.getMessage().getChat().getId();    
	        // Set variables
	        String message_text = update.getMessage().getText();
	        long chat_id = update.getMessage().getChatId();

	        String answer="Your request is logged now";
	        SendMessage message = new SendMessage() // Create a message object object
	                .setChatId(chat_id)
	                .setText(answer);
	        
            log(user_first_name, user_last_name, Long.toString(user_id), message_text, answer);

	        try {
	            execute(message); // Sending our message object to user
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
	}

	@Override
	public String getBotUsername() {
		return "gauravjavabot";
	}

	@Override
	public String getBotToken() {
		return "1287994026:AAETaF6BeGSM0Yc2ZTld5xvIOWdFxwhrktU";
	}
	
	private void log(String first_name, String last_name, String user_id, String txt, String bot_answer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + first_name + " " + last_name + ". (id = " + user_id + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + bot_answer);
    }

}
