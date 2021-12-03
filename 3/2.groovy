def readFile() {
  def data = []
  new File('input').eachLine { line ->
    data << line
  }
  return data
}

def getRatings(data, position, gl) {
  int winner = 0

  for (int i = 0; i < data.size(); i++) {
    winner += data[i][position] == '1' ? 1 : -1
  }

  String check

  if (gl) {
    check = winner >= 0 ? '1' : '0'
  } else {
    check = winner >= 0 ? '0' : '1'
  }

  // Parentheses not required when closure is last parameter.  This is the preferred usage.
  data = data.findAll{ it[position] == check }
  position++

  if (data.size() > 1) {
    data = getRatings(data, position, gl)
  }
  return data
}

def lifeSupport() {
  data = readFile()

  (ogRating) = getRatings(data, 0, 1)
  (co2Rating) = getRatings(data, 0, 0)

  return Integer.parseInt(ogRating, 2) * Integer.parseInt(co2Rating, 2)
}

println lifeSupport()
