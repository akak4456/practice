//
//  EmojiMemoryGame.swift
//  Memorize
//
//  Created by Choi Ayoung on 2022/05/20.
//

import SwiftUI

func makeCardContent(index: Int) -> String {
  return "🐵"
}


class EmojiMemoryGame {
  private var model: MemoryGame<String> = MemoryGame<String>(numberOfPairsOfCards: 4, createCardContent: makeCardContent)
  
  var cards: Array<MemoryGame<String>.Card> {
    return model.cards
  }
}
