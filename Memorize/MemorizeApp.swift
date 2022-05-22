//
//  MemorizeApp.swift
//  Memorize
//
//  Created by Choi Ayoung on 2022/05/15.
//

import SwiftUI

@main
struct MemorizeApp: App {
  let game = EmojiMemoryGame()
  var body: some Scene {
    WindowGroup {
      ContentView(viewModel: game)
    }
  }
}

/*
 앞으로 읽기 과제는 여기에다가 진행하도록 하자!
 Lecture 2
 후행 클로저는 아무래도 뒤에다 쓰는 클로저인듯?
 Lecture 3
 */
