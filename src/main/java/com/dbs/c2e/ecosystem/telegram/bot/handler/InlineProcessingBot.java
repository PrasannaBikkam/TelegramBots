package com.dbs.c2e.ecosystem.telegram.bot.handler;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResult;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.InlineQueryResultArticle;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class InlineProcessingBot extends TelegramLongPollingBot {

	@Override
	public void onUpdateReceived(Update update) {
		if (update.hasCallbackQuery()) {
			EditMessageText message = null;
			if (update.getCallbackQuery().getData().equals("removeKeyboard")) {

				InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> keyboard = replyKeyboardMarkup.getKeyboard();
				replyKeyboardMarkup.setKeyboard(keyboard);

				message = new EditMessageText();
				message.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
				message.setReplyMarkup(replyKeyboardMarkup);
				message.setText("Payment Canceled");

			} 
			else if (update.getCallbackQuery().getData().equals("paymentSuccess")) {

				InlineKeyboardMarkup replyKeyboardMarkup = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> keyboard = replyKeyboardMarkup.getKeyboard();
				replyKeyboardMarkup.setKeyboard(keyboard);

				message = new EditMessageText();
				message.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
				message.setReplyMarkup(replyKeyboardMarkup);
				message.setText("Payment Success");

			} 
			else if (update.getCallbackQuery().getData().equals("abc")) {
				ReplyKeyboardMarkup reply = new ReplyKeyboardMarkup();
				reply.setOneTimeKeyboard(true).setSelective(true);
//			InlineKeyboardMarkup inlineKeyboardMarkup = update.getCallbackQuery().getMessage().getReplyMarkup();
				InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> keyboard = inlineKeyboardMarkup.getKeyboard();
				for (int i = 0; i < keyboard.size(); i++) {
					keyboard.remove(i);
				}
				List<InlineKeyboardButton> row1 = new ArrayList<>();
				row1.add(new InlineKeyboardButton("Confirm").setUrl("www.google.com").setCallbackData("paymentSuccess"));
				keyboard.add(row1);
				List<InlineKeyboardButton> row2 = new ArrayList<>();
				row2.add(new InlineKeyboardButton("Cancel").setCallbackData("removeKeyboard").setCallbackData("removeKeyboard"));
				keyboard.add(row2);
				inlineKeyboardMarkup.setKeyboard(keyboard);
				String editedMsg = update.getCallbackQuery().getFrom().getFirstName() + " accepted amount request";
				message = new EditMessageText();
				message.setReplyMarkup(inlineKeyboardMarkup);
				message.setText(editedMsg);
				message.setInlineMessageId(update.getCallbackQuery().getInlineMessageId());
				message.enableMarkdown(true);
			}
			try {
				execute(message); // Sending our message object to user
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		}
//		if (update.hasChosenInlineQuery()) {
//			update.getChosenInlineQuery().getInlineMessageId();
//			System.out.println(update.getChosenInlineQuery().getResultId());
//			update.getInlineQuery();
//			AnswerInlineQuery reply = new AnswerInlineQuery();
//
////			InputMessageContent imc = new InputTextMessageContent().setMessageText("You are not registered");
//
//			InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//			List<List<InlineKeyboardButton>> keyboard = inlineKeyboardMarkup.getKeyboard();
//			List<InlineKeyboardButton> row1 = new ArrayList<>();
//			row1.add(new InlineKeyboardButton("Confirm").setUrl("www.google.com").setCallbackData("abc"));
//			keyboard.add(row1);
//			List<InlineKeyboardButton> row2 = new ArrayList<>();
//			row2.add(new InlineKeyboardButton("Cancel").setCallbackData("abc"));
//			keyboard.add(row2);
//			inlineKeyboardMarkup.setKeyboard(keyboard);
//			InputMessageContent imc = new InputTextMessageContent().setMessageText("Pay Now");
////			
//			InlineQueryResult iqr = new InlineQueryResultArticle()
//					.setId(update.getChosenInlineQuery().getResultId() + 1).setReplyMarkup(inlineKeyboardMarkup)
//					.setTitle("abc").setInputMessageContent(imc);
//
//			reply.setInlineQueryId(update.getChosenInlineQuery().getResultId()).setResults(iqr).setPersonal(true);
//
////			EditMessageReplyMarkup editMsg = new EditMessageReplyMarkup();
////			editMsg.setInlineMessageId(update.getChosenInlineQuery().getResultId());
////			editMsg.setText("Confirm to send money");
////			editMsg.setReplyMarkup(inlineKeyboardMarkup);
//
////			InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
////			List<List<InlineKeyboardButton>> keyboard= inlineKeyboardMarkup.getKeyboard();
////			List<InlineKeyboardButton> row1 = new ArrayList<>();
////			row1.add(new InlineKeyboardButton("Confirm").setUrl("market://details?id=com.dbs.di").setCallbackData("abc"));
////			keyboard.add(row1);
////			List<InlineKeyboardButton> row2 = new ArrayList<>();
////			row2.add(new InlineKeyboardButton("Cancel").setCallbackData("abc"));
////			keyboard.add(row2);
////			inlineKeyboardMarkup.setKeyboard(keyboard);
//////			reply.setId("send");
//////			reply.setReplyMarkup(inlineKeyboardMarkup)
////			
//			SendMessage message = new SendMessage();
//			message.setChatId(update.getChosenInlineQuery().getFrom().getId().toString());
//			message.setReplyMarkup(inlineKeyboardMarkup);
//			message.setText("Confirm Payment");
//			message.enableMarkdown(true);
//			EditMessageText editMsg = new EditMessageText();
//			editMsg.setInlineMessageId(update.getChosenInlineQuery().getResultId());
//			editMsg.setReplyMarkup(inlineKeyboardMarkup);
//			editMsg.setText("Confirm Payment");
//
//			try {
//				execute(editMsg); // Sending our message object to user
//			} catch (TelegramApiException e) {
//				e.printStackTrace();
//			}
//		}

		if (update.hasInlineQuery()) {
			String inlineQueryId = update.getInlineQuery().getId();
			int fromUserId = update.getInlineQuery().getFrom().getId();
			String senderName = update.getInlineQuery().getFrom().getFirstName() + " "
					+ update.getInlineQuery().getFrom().getLastName();
			String inlineQuery = update.getInlineQuery().getQuery();

			// call to validate the link for this fromUserId
			// link returned false
			boolean isRegistered = true;

			AnswerInlineQuery reply = new AnswerInlineQuery();

			if (!isRegistered) {

				InputMessageContent imc = new InputTextMessageContent().setMessageText("You are not registered");

				InlineQueryResult iqr = new InlineQueryResultArticle().setId(inlineQueryId + 101)
						.setInputMessageContent(imc).setTitle("You are not registered");

				reply.setInlineQueryId(inlineQueryId).setResults(iqr).setPersonal(true).setCacheTime(0)
						.setSwitchPmText("You are not registered please click the link to register")
						.setSwitchPmParameter("register");

				try {
					execute(reply); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			} else {

				List<InlineQueryResult> list = new ArrayList<>();
				InputMessageContent imc = new InputTextMessageContent()
						.setMessageText(senderName + " is sending $" + inlineQuery);
				InputMessageContent imc1 = new InputTextMessageContent()
						.setMessageText(senderName + " requesting for $" + inlineQuery);
				InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
				List<List<InlineKeyboardButton>> keyboard = inlineKeyboardMarkup.getKeyboard();
				List<InlineKeyboardButton> row1 = new ArrayList<>();
				row1.add(new InlineKeyboardButton("Receive $" + inlineQuery + " from " + senderName)
						.setCallbackData("abc"));
				keyboard.add(row1);
				List<InlineKeyboardButton> row2 = new ArrayList<>();
				row2.add(new InlineKeyboardButton("Reject").setCallbackData("removeKeyboard"));
				keyboard.add(row2);
				inlineKeyboardMarkup.setKeyboard(keyboard);
				InlineQueryResult req = new InlineQueryResultArticle().setId(inlineQueryId + 103)
						.setInputMessageContent(imc1).setTitle("Request $" + inlineQuery)
						.setReplyMarkup(inlineKeyboardMarkup);
				System.out.println(inlineQueryId + 104);
				InlineQueryResult send = new InlineQueryResultArticle().setId(inlineQueryId + 104)
						.setInputMessageContent(imc).setTitle("Send $" + inlineQuery)
						.setReplyMarkup(inlineKeyboardMarkup);
				;
				list.add(req);
				list.add(send);
				reply.setInlineQueryId(inlineQueryId).setResults(list).setPersonal(true);

				try {
					execute(reply); // Sending our message object to user
				} catch (TelegramApiException e) {
					e.printStackTrace();
				}
			}

		}

		else if (update.hasMessage()) {
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
		return "Durga1bot";
	}

	@Override
	public String getBotToken() {
		return "1320336253:AAH_fr9StOyRlCJq_BPCXQm1dO_fy5HiY0c";
	}

}
