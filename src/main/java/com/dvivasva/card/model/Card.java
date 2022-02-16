package com.dvivasva.card.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Card {
    @Id
    private String id;
    private String number;
    private String keyATM;
    private String keyInternet;
    private String accountId;

}
