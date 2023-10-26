package com.untact.persistent.vocabularyitem;

import org.springframework.data.jpa.repository.JpaRepository;

import com.untact.domain.vocabularyitem.VocabularyItem;

public interface VocabularyItemRepository extends JpaRepository<VocabularyItem, Long>, VocabularyItemCustomRepository {

}
