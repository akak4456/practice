//
//  ContentView.swift
//  StandfordLecture2
//
//  Created by Choi Ayoung on 2022/05/17.
//

import SwiftUI

struct ContentView: View {
  var emojis: [String] = ["🚗","🚕","🚙","🚌","🚎","🏎","🚓","🚑","🚒","🚐","🛻","🚚","🚛","🚜","🦽"
                          ,"🦼","🛴","🚲","🛵","🏍","🛺","🚔","🚍","🚘"]
  
  @State var emojiCount = 4
  var body: some View {
    VStack {
      ScrollView {
        LazyVGrid(columns: [GridItem(.adaptive(minimum: 65))] ) {
          ForEach(emojis[0..<emojiCount], id: \.self) { emoji in
            CardView(emoji: emoji).aspectRatio(2/3, contentMode: .fit)
          }
        }
      }
      Spacer()
      HStack {
        remove
        Spacer()
        add
      }.font(.largeTitle)
    }.padding()
  }
  
  var remove: some View {
    Button {
      if emojiCount > 1 {
        emojiCount -= 1
      }
    }
  label: {
    Image(systemName: "minus.circle")
  }
  }
  
  var add: some View {
    Button {
      if emojiCount < emojis.count {
        emojiCount += 1
      }
    }
  label: {
    Image(systemName: "plus.circle")
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
