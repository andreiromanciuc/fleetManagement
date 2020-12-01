package com.andrei.fleetManagement.service;

import com.andrei.fleetManagement.domain.Contract;
import com.andrei.fleetManagement.domain.Message;
import com.andrei.fleetManagement.exception.ResourceNotFoundExceptions;
import com.andrei.fleetManagement.persistance.MessageRepository;
import com.andrei.fleetManagement.transfer.CreateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@Service
public class MessageService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final MessageRepository messageRepository;
    private final ContractService contractService;

    public MessageService(MessageRepository messageRepository,
                          ContractService contractService) {
        this.messageRepository = messageRepository;
        this.contractService = contractService;
    }

    public Message createMessage(long contractId, CreateMessage createMessage) {
        LOGGER.info("Creating message");

        Contract contract = contractService.getContractById(contractId);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Bucharest"));
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) +1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        Message message = new Message();
        message.setDateOfMessage(day + "/"+ month +"/"+ year);
        message.setContent(createMessage.getContent());
        message.setOwner(createMessage.getOwner());
        message.setUnread(true);
        message.setContract(contract);

        return message;
    }

    public List<Message> getMessagesByContract(long contractId) {
        LOGGER.info("Retrieving messages for contract {}", contractId);

        return messageRepository.findAllByContract(contractId);
    }

    public Message editMessage(long messageId, String content) {
        LOGGER.info("Editing message {}", messageId);
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new ResourceNotFoundExceptions("This message was not found"));

        Date date = new Date();
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Bucharest"));
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        message.setDateOfMessage(day + "/"+ month +"/"+ year);
        message.setContent(content);

        return message;
    }

    public void deleteMessage(long id) {
        LOGGER.info("Deleting message");
        messageRepository.deleteById(id);
    }
}
