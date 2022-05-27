//
//  ContentView.swift
//  StanfordLecture4
//
//  Created by Choi Ayoung on 2022/05/25.
//

import SwiftUI

struct ContentView: View {
  @ObservedObject var viewModel: EmojiMemoryGame
    var body: some View {
      VStack {
        Text("Score: 0").font(.largeTitle)
        ScrollView {
          LazyVGrid(columns: [GridItem(.adaptive(minimum: 65))]) {
            ForEach(viewModel.cards) { card in
              CardView(card: card)
                .aspectRatio(2/3, contentMode: .fit)
                .onTapGesture {
                  viewModel.choose(card)
                }
            }
          }
        }
        .foregroundColor(getColorByString(viewModel.currentCardColor))
        .padding(.horizontal)
        Button(action: {
          viewModel.startNewGame()
        }) {
          Text("New Game").font(.largeTitle)
        }.padding(.vertical)
      }
      
    }
  
  func getColorByString(_ colorString: String) -> Color {
    switch colorString {
    case "red": return Color.red
    case "blue": return Color.blue
    case "orange": return Color.orange
    case "gray": return Color.gray
    default: return Color.red
    }
  }
}

struct CardView: View {
  let card: MemoryGame<String>.Card
  
  var body: some View {
    ZStack {
      let shape = RoundedRectangle(cornerRadius: 20)
      if card.isFaceUp {
        shape.fill().foregroundColor(.white)
        shape.strokeBorder(lineWidth: 3)
        Text(card.content).font(.largeTitle)
      } else if card.isMatched {
        shape.opacity(0)
      } else {
        shape.fill()
      }
    }
  }
}
