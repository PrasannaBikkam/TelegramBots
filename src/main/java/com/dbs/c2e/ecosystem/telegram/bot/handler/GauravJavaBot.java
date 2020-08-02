package com.dbs.c2e.ecosystem.telegram.bot.handler;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class GauravJavaBot extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage()) {
			Message recieved_msg = update.getMessage();
			long chat_id = recieved_msg.getChatId();
			SendMessage message = null;
			// Set variables
			String message_text = update.getMessage().getText();
			message = registerHandler(message_text, chat_id);
			message = contactHandler(recieved_msg, chat_id, message);
			if (message == null) {
				message = new SendMessage() // Create a message object object
						.setChatId(chat_id).setText("click /register to register");
			}
			try {
				execute(message); // Sending our message object to user
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
	}

	public SendMessage registerHandler(String message_text, long chat_id) {
		SendMessage message = null;
		if (message_text != null && message_text.equals("/register")) {
			List list = new ArrayList<>();
			KeyboardRow keyboardRow = new KeyboardRow();
			keyboardRow.add(new KeyboardButton("Register Now").setRequestContact(true));
			list.add(keyboardRow);

			message = new SendMessage() // Create a message object object
					.setChatId(chat_id).setText("click the below button to register with paylahBot")
					.setReplyMarkup(new ReplyKeyboardMarkup(list).setOneTimeKeyboard(true).setResizeKeyboard(true)
							.setSelective(true));

		}
		return message;
	}

	public SendMessage contactHandler(Message recieved_msg, long chat_id, SendMessage modifiedMsg) {
		if (modifiedMsg == null && recieved_msg.hasContact()) {

			modifiedMsg = new SendMessage() // Create a message object object
					.setChatId(chat_id)
					.setText("Your Phone number has been updated! " + recieved_msg.getContact().getPhoneNumber());

		}
		return modifiedMsg;
	}

	@Override
	public String getBotUsername() {
		return "gauravjavabot";
	}

	@Override
	public String getBotToken() {
		return "1287994026:AAETaF6BeGSM0Yc2ZTld5xvIOWdFxwhrktU";
	}

}
