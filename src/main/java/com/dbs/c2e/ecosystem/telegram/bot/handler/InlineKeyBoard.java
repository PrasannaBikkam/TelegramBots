package com.dbs.c2e.ecosystem.telegram.bot.handler;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class InlineKeyBoard extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			// Set variables
			long chat_id = update.getMessage().getChatId();
			if (update.getMessage().getText().equals("/start")) {

				SendMessage message = new SendMessage() // Create a message object object
						.setChatId(chat_id)
						.setText("You send /start");
				InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
				List<InlineKeyboardButton> rowInline = new ArrayList<>();
				rowInline.add(new InlineKeyboardButton().setText("Update message text").setCallbackData("update_msg_text"));
				// Set the keyboard to the markup
				rowsInline.add(rowInline);
				// Add it to the message
				markupInline.setKeyboard(rowsInline);
				message.setReplyMarkup(markupInline);
				try {
					execute(message); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}
		}
		else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("update_msg_text")) {
                String answer = "Updated message text";
                EditMessageText new_message = new EditMessageText()
                        .setChatId(chat_id)
                        .setMessageId(Math.toIntExact(message_id))
                        .setText(answer);
                try {
                    execute(new_message); 
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
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

}
