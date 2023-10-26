//
//  EmojiMemoryGame.swift
//  StanfordLecture4
//
//  Created by Choi Ayoung on 2022/05/25.
//

import Foundation

class EmojiMemoryGame: ObservableObject {
  
  
  static let themeList: [Theme] = [
    Theme(name: "대중교통", setOfEmojis: Theme.theme1, numberOfPairsOfCards: 5, color: "red"),
    Theme(name: "동물", setOfEmojis: Theme.theme2, numberOfPairsOfCards: 6, color: "blue"),
    Theme(name: "표정", setOfEmojis: Theme.theme3, numberOfPairsOfCards: 3, color: "orange"),
    Theme(name: "음식", setOfEmojis: Theme.theme4, numberOfPairsOfCards: 8, color: "gray"),
    Theme(name: "운동", setOfEmojis: Theme.theme5, numberOfPairsOfCards: 10, color: "red"),
    Theme(name: "사물", setOfEmojis: Theme.theme6, numberOfPairsOfCards: 7, color: "blue")
  ]
  
  static func createMemoryGame(theme: Theme) -> MemoryGame<String> {
    var validNumberOfPairsOfCards = theme.numberOfPairsOfCards
    if(theme.setOfEmojis.count < validNumberOfPairsOfCards) {
      validNumberOfPairsOfCards = theme.setOfEmojis.count
    }
    return MemoryGame<String>(numberOfPairsOfCards: validNumberOfPairsOfCards) {
      theme.setOfEmojis[$0]
    }
  }
  
  static let initialTheme: Theme = themeList[1]
  
  @Published private var model: MemoryGame<String> = createMemoryGame(theme: initialTheme)
  
  @Published private var currentTheme: Theme = initialTheme
  
  @Published private(set) var score: Int = 0
  
  
  var cards: Array<MemoryGame<String>.Card> {
    model.cards
  }
  
  var currentCardColor: String {
    currentTheme.color
  }
  
  // MARK: - Intent(s)
  
  func choose(_ card: MemoryGame<String>.Card) {
    let chooseState = model.choose(card)
    if chooseState == .not_matched {
      score -= 2
    } else if chooseState == .matched {
      score += 1
    }
  }
  
  func startNewGame() {
    if let newTheme = EmojiMemoryGame.themeList.randomElement() {
      currentTheme = newTheme
      model = EmojiMemoryGame.createMemoryGame(theme: newTheme)
    }
  }

}
