//
//  EmojiMemoryGame.swift
//  StanfordLecture4
//
//  Created by Choi Ayoung on 2022/05/25.
//

import Foundation

class EmojiMemoryGame: ObservableObject {
  static let theme1: [String] = ["🚗","🚕","🚙","🚌","🚎","🏎","🚓","🚑","🚒","🚐","🛻","🚚","🚛","🚜","🦽"
                          ,"🦼","🛴","🚲","🛵","🏍","🛺","🚔","🚍","🚘"]
  
  static let theme2: [String] = ["🐶","🐱","🐭","🐹","🐰","🦊","🐻","🐼","🐻‍❄️","🐨"]
  
  static let theme3: [String] = ["😀", "😃", "😄", "😁", "😆","😅", "😂"]
  
  static let theme4: [String] = ["🍏","🍎","🍐","🍊","🍋","🍌","🍉","🍇","🍓","🫐","🍈","🍒","🍑","🥭"]
  
  static let theme5: [String] = ["⚽️","🏀","🏈","⚾️"]
  
  static let theme6: [String] = ["⌚️","📱","💻","📲","⌨️","🖥","🖨"]
  
  static func createMemoryGame() -> MemoryGame<String> {
    MemoryGame<String>(numberOfPairsOfCards: 3) {
      theme1[$0]
    }
  }
  @Published private var model: MemoryGame<String> = createMemoryGame()
  
  var cards: Array<MemoryGame<String>.Card> {
    model.cards
  }
  
  // MARK: - Intent(s)
  
  func choose(_ card: MemoryGame<String>.Card) {
    model.choose(card)
  }
}
