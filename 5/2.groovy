def loadInput() {
  def data = [:]
  new File('input').eachLine {
    (x1, y1, x2, y2) = it.replaceAll(' -> ', ',').split(',')

    x1 = x1 as int
    y1 = y1 as int
    x2 = x2 as int
    y2 = y2 as int

    if (x1 == x2) {
      int min = [y1, y2].min()
      int max = [y1, y2].max()

      while (min <= max) {
        try { data["$x1|$min"]++ } catch (exc) { data["$x1|$min"] = 1 }
        min++
      }
    }
    else if (y1 == y2) {
      int min = [x1, x2].min()
      int max = [x1, x2].max()

      while (min <= max) {
        try { data["$min|$y1"]++ } catch (exc) { data["$min|$y1"] = 1 }
        min++
      }
    }
    else if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
      int xDir = x1 > x2 ? -1 : 1
      int yDir = y1 > y2 ? -1 : 1
      int total = Math.abs(x1 - x2)

      for (int i = 0; i <= total; i++) {
        try { data["$x1|$y1"]++ } catch (exc) { data["$x1|$y1"] = 1 }
        x1 += xDir
        y1 += yDir
      }
    }
  }
  return data
}

def findOverlap() {
  // Last statement will return automatically
  loadInput().findAll{ it.value >= 2 }.size()
}

println findOverlap()
