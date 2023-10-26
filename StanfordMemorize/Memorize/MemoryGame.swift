//
//  MemoryGame.swift
//  Memorize
//
//  Created by Choi Ayoung on 2022/05/20.
//

import Foundation

struct MemoryGame<CardContent> where CardContent: Equatable{
  private(set) var cards: Array<Card>
  
  private var indexOfTheOneAndOnlyFaceUpCard: Int?
  
  mutating func choose(_ card: Card) {
    if let chosenIndex = cards.firstIndex(where: { $0.id == card.id}),
       !cards[chosenIndex].isFaceUp,
       !cards[chosenIndex].isMatched
    {
      if let potentialMatchIndex = indexOfTheOneAndOnlyFaceUpCard {
        if cards[chosenIndex].content == cards[potentialMatchIndex].content {
          cards[chosenIndex].isMatched = true
          cards[potentialMatchIndex].isMatched = true
        }
        indexOfTheOneAndOnlyFaceUpCard = nil
      } else {
        for index in cards.indices {
          cards[index].isFaceUp = false
        }
        indexOfTheOneAndOnlyFaceUpCard = chosenIndex
      }
      cards[chosenIndex].isFaceUp.toggle()
    }
    print("\(cards)")
  }
  
  init(numberOfPairsOfCards: Int, createCardContent: (Int) -> CardContent) {
    cards = []
    // add numberOfPairsOfCards X 2 cards to cards array
    
    for pairIndex in 0..<numberOfPairsOfCards {
      let content = createCardContent(pairIndex)
      cards.append(Card(id: pairIndex * 2, content: content))
      cards.append(Card(id: pairIndex * 2 + 1, content: content))
    }
  }
  
  struct Card: Identifiable {
    let id: Int
    
    var isFaceUp = false
    var isMatched = false
    let content: CardContent
  }
}
