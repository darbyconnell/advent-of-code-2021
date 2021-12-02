def readInput() {
  def input = []

  new File('input').eachLine { line ->
    input << line
  }

  return input
}

def position() {
  int position = 0
  int depth = 0
  int aim = 0
  def items = readInput()

  for (int i = 0; i < items.size(); i++) {
    String[] data
    data = items[i].split(' ')

    if (data[0] == 'forward') {
      position += data[1] as int
      depth += aim * (data[1] as int)
    }
    else if (data[0] == 'up') {
      aim -= data[1] as int
    }
    else if (data[0] == 'down') {
      aim += data[1] as int
    }
  }

  return position * depth
}

println position()
