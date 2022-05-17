//
//  ContentView.swift
//  StandfordLecture2
//
//  Created by Choi Ayoung on 2022/05/17.
//

import SwiftUI

struct ContentView: View {
  let vehicleTheme: [String] = ["🚗","🚕","🚙","🚌","🚎","🏎","🚓","🚑","🚒","🚐","🛻","🚚","🚛","🚜","🦽"
                                ,"🦼","🛴","🚲","🛵","🏍","🛺","🚔","🚍","🚘"]
  let emotionTheme: [String] = ["😀","😃","😇","🥰","🤬","🥵","🤣","😤","👺","🤖"]
  let animalTheme: [String] = ["🐶","🐱","🐭","🐹","🐰","🦊","🐻","🐼","🐻‍❄️","🐨","🐯","🦁","🐮","🐷"]
  @State var emojis: [String] = ["🚗","🚕","🚙","🚌","🚎","🏎","🚓","🚑","🚒","🚐","🛻","🚚","🚛","🚜","🦽"
                                 ,"🦼","🛴","🚲","🛵","🏍","🛺","🚔","🚍","🚘"]
  
  var body: some View {
    VStack {
      Text("Memorize!").font(.largeTitle)
      ScrollView {
        LazyVGrid(columns: [GridItem(.adaptive(minimum: 65))] ) {
          ForEach(emojis, id: \.self) { emoji in
            CardView(emoji: emoji).aspectRatio(2/3, contentMode: .fit)
          }
        }
      }
      Spacer()
      HStack {
        Spacer()
        theme1
        Spacer()
        theme2
        Spacer()
        theme3
        Spacer()
      }
    }.padding()
  }
  
  var theme1: some View {
    Button {
      emojis = vehicleTheme
      emojis.shuffle()
    }
  label: {
    VStack {
      Image(systemName: "minus.circle").font(.largeTitle)
      Text("theme1").font(.caption)
    }
  }
  }
  
  var theme2: some View {
    Button {
      emojis = emotionTheme
      emojis.shuffle()
    }
  label: {
    VStack {
      Image(systemName: "minus.circle").font(.largeTitle)
      Text("theme2").font(.caption)
    }
  }
  }
  
  var theme3: some View {
    Button {
      emojis = animalTheme
      emojis.shuffle()
    }
  label: {
    VStack {
      Image(systemName: "minus.circle").font(.largeTitle)
      Text("theme3").font(.caption)
    }
  }
  }
  
}

struct CardView: View {
  var emoji: String
  @State var isFaceUp = false
  var body: some View {
    ZStack {
      if isFaceUp {
        RoundedRectangle(cornerRadius: 10).foregroundColor(.red)
      } else {
        RoundedRectangle(cornerRadius: 10)
          .fill()
          .foregroundColor(.white)
        RoundedRectangle(cornerRadius: 10)
          .strokeBorder(lineWidth: 3)
          .foregroundColor(.red)
        Text(emoji)
          .font(.largeTitle)
      }
    }.onTapGesture{
      isFaceUp = !isFaceUp
    }
  }
}
