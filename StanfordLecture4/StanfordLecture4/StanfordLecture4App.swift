//
//  StanfordLecture4App.swift
//  StanfordLecture4
//
//  Created by Choi Ayoung on 2022/05/25.
//

import SwiftUI

@main
struct StanfordLecture4App: App {
  let emojiMemoryGame = EmojiMemoryGame()
    var body: some Scene {
        WindowGroup {
            ContentView(viewModel: emojiMemoryGame)
        }
    }
}
