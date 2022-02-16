package com.dvivasva.card.service;

import com.dvivasva.card.dto.CardDto;
import com.dvivasva.card.repository.CardRepository;
import com.dvivasva.card.utils.CardUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CardService {


    private final CardRepository cardRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public Mono<CardDto> create(Mono<CardDto> entityToDto){

        Mono<CardDto> result=entityToDto.map(
                p -> {
                    p.setKeyATM(bCryptPasswordEncoder.encode(p.getKeyATM()));
                    p.setKeyInternet(bCryptPasswordEncoder.encode(p.getKeyInternet()));
                    return p;
                });
        return result.map(CardUtil::dtoToEntity)
                .flatMap(cardRepository::save)
                .map(CardUtil::entityToDto);

    }
    public Mono<CardDto> updateCard(Mono<CardDto> cardDtoMono,String id){
        Mono<CardDto> result=cardDtoMono.map(
                p -> {
                    p.setKeyATM(bCryptPasswordEncoder.encode(p.getKeyATM()));
                    p.setKeyInternet(bCryptPasswordEncoder.encode(p.getKeyInternet()));
                    return p;
                });
        return cardRepository.findById(id)
                .flatMap(p->result.map(CardUtil::dtoToEntity)
                        .doOnNext(e->e.setId(id)))
                .flatMap(cardRepository::save)
                .map(CardUtil::entityToDto);

    }

}
