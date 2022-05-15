//
//  ContentView.swift
//  Memorize
//
//  Created by Choi Ayoung on 2022/05/15.
//

import SwiftUI

struct ContentView: View {
  var emojis: [String] = ["🚗","🚕","🚙","🚌","🚎","🏎","🚓","🚑","🚒","🚐","🛻","🚚","🚛","🚜","🦽"
                          ,"🦼","🛴","🚲","🛵","🏍","🛺","🚔","🚍","🚘"]
  @State var emojiCount = 4
  var body: some View {
    
    VStack{
      HStack {
        ForEach(emojis[0..<emojiCount], id: \.self) { emoji in
          CardView(content: emoji)
        }
      }
      HStack{
        remove
        Spacer()
        add
      }.padding(.horizontal)
    }
    .padding(.horizontal)
    .foregroundColor(.red)
  }
  
  var remove: some View {
    Button(action: {
      emojiCount -= 1
    }, label: {
      VStack {
        Text("Remove")
        Text("Card")
      }
    })
  }
  
  var add: some View {
    Button(action: {
      emojiCount += 1
    }, label: {
      VStack {
        Text("Add")
        Text("Card")
      }
    })
  }
}

struct CardView: View {
  var content: String
  @State var isFaceUp: Bool = true
  
  var body: some View {
    ZStack {
      let shape = RoundedRectangle(cornerRadius: 20)
      if isFaceUp {
        shape.fill().foregroundColor(.white)
        shape.stroke(lineWidth: 3)
        Text(content).font(.largeTitle)
      } else {
        shape.fill()
      }
    }
    .onTapGesture {
      isFaceUp = !isFaceUp
    }
  }
}

struct ContentView_Previews: PreviewProvider {
  static var previews: some View {
    ContentView()
  }
}
