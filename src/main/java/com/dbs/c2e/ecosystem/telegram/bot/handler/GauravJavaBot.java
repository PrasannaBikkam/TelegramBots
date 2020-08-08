package com.dbs.c2e.ecosystem.telegram.bot.handler;

import java.util.ArrayList;
import java.util.List;

import org.checkerframework.checker.units.qual.m;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
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
			message = registerHandler(recieved_msg,message_text, chat_id);
			AnswerCallbackQuery callbackQuery = contactHandler(recieved_msg, chat_id, message);
			if (callbackQuery != null) {
				try {
					execute(callbackQuery);
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
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

	public SendMessage registerHandler(Message recieved_msg,String message_text, long chat_id) {
		SendMessage message = null;
		if (message_text != null && message_text.equals("/register")) {
//			List list = new ArrayList<>();
//			KeyboardRow keyboardRow = new KeyboardRow();
//			keyboardRow.add(new KeyboardButton("Register Now").setRequestContact(true));
//			list.add(keyboardRow);
			ReplyKeyboard markup = new InlineKeyboardMarkup();
			final List<List<InlineKeyboardButton>> keyboard = ((InlineKeyboardMarkup) markup).getKeyboard();
			List<InlineKeyboardButton> list = new ArrayList<InlineKeyboardButton>();
			keyboard.add(list);
			keyboard.get(0).add(new InlineKeyboardButton().setText("Register Now").setCallbackData("Id123"));
//			message = new SendMessage() // Create a message object object
//					.setChatId(chat_id).setText("click the below button to register with paylahBot")
//					.setReplyMarkup(new InlineKeyboardMarkup(list).setOneTimeKeyboard(true).setResizeKeyboard(true)
//							.setSelective(true));

			message = new SendMessage();
			message.setChatId(chat_id);
//			message.setText("Your text here");
			message.setReplyToMessageId(recieved_msg.getMessageId());
			message.setReplyMarkup(markup);

		}
		return message;
	}

	public AnswerCallbackQuery contactHandler(Message recieved_msg, long chat_id, SendMessage modifiedMsg) {
		if (modifiedMsg == null && recieved_msg.hasContact()) {
			String toEscapeMsg = "&lt;html&gt;&lt;head&gt;&lt;meta http-equiv = &quot;refresh&quot; content = &quot;2; url = https://www.tutorialspoint.com&quot; /&gt;&lt;/head&gt;&lt;/html&gt;";
			String escapedMsg = toEscapeMsg.replace("_", "\\_").replace("*", "\\*").replace("[", "\\[").replace("`",
					"\\`");
			AnswerCallbackQuery callback = new AnswerCallbackQuery();
			callback.setUrl("www.google.com");
			callback.setCallbackQueryId("Id123");
			return callback;
		}
		return null;
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
