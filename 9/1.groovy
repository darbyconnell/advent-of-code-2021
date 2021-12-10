def loadInput() {
  def output = []
  new File('input').eachLine {
    output << it.trim()
  }
  return output
}

def isLowPoint(y, x, heightMap) {
  int maxY = heightMap.size() - 1
  int maxX = heightMap[0].size() - 1
  y = y as int
  x = x as int

  if ((y > 0 && heightMap[y-1][x] <= heightMap[y][x])
    || (y < maxY && heightMap[y+1][x] <= heightMap[y][x])
    || (x > 0 && heightMap[y][x-1] <= heightMap[y][x])
    || (x < maxX && heightMap[y][x+1] <= heightMap[y][x])) {
    return false
  }

  return true
}

def riskLevel() {
  def heightMap = loadInput()
  int risk = 0

  for (int y = 0; y < heightMap.size(); y++) {
    for (int x = 0; x < heightMap[y].size(); x++) {
      if (isLowPoint(y, x, heightMap)) {
        risk += (heightMap[y][x] as int) + 1
      }
    }
  }

  return risk
}

println riskLevel()
