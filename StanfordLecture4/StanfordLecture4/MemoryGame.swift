//
//  MemoryGame.swift
//  StanfordLecture4
//
//  Created by Choi Ayoung on 2022/05/25.
//

import Foundation

struct MemoryGame<CardContent> where CardContent: Equatable {
  private(set) var cards: Array<Card>
  
  private var indexOfTheOneAndOnlyOneFaceCard: Int?
  
  init(numberOfPairsOfCards: Int, createCardContent: (Int) -> CardContent) {
    cards = Array<Card>()
    
    for pairIndex in 0..<numberOfPairsOfCards {
      let content = createCardContent(pairIndex)
      cards.append(Card(id: pairIndex * 2, content: content))
      cards.append(Card(id: pairIndex * 2 + 1, content: content))
    }
    
    cards.shuffle()
  }
  
  mutating func choose(_ card: MemoryGame<CardContent>.Card) -> ChooseState {
    var chooseState: ChooseState = .ongoing
    if let chosenIndex = cards.firstIndex(where: { $0.id == card.id}),
       !cards[chosenIndex].isFaceUp,
       !cards[chosenIndex].isMatched
    {
      if let potentialMatchIndex = indexOfTheOneAndOnlyOneFaceCard {
        if cards[chosenIndex].content == cards[potentialMatchIndex].content {
          cards[chosenIndex].isMatched = true
          cards[potentialMatchIndex].isMatched = true
          chooseState = .matched
        } else {
          chooseState = .not_matched
        }
        indexOfTheOneAndOnlyOneFaceCard = nil
      } else {
        for index in cards.indices {
          cards[index].isFaceUp = false
        }
        indexOfTheOneAndOnlyOneFaceCard = chosenIndex
      }
      cards[chosenIndex].isFaceUp.toggle()
    }
    return chooseState
  }
  
  struct Card: Identifiable {
    var id: Int
    
    var isFaceUp: Bool = false
    var isMatched: Bool = false
    var content: CardContent
  }
  
  enum ChooseState {
    case ongoing
    case not_matched
    case matched
  }
}
