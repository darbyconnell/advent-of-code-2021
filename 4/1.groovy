def loadInput() {
  def numbers = []
  def grids = []
  int gridPos = -1

  new File('input').eachLine { line ->
    if (!numbers.size()) {
      numbers = line.split(',')
    } else if (!line.size()) {
      gridPos++
      grids[gridPos] = [:]
      grids[gridPos]['rows'] = []
      grids[gridPos]['cols'] = []
    } else {
      def row = line.split(' ').findAll{ it.size() }
      grids[gridPos]['rows'].add(row)
      row.eachWithIndex{ it, i ->
        try { grids[gridPos]['cols'][i] << it } catch (exc) { grids[gridPos]['cols'][i] = [it] }
      }
    }
  }

  return [numbers, grids]
}

def remove(num, grids, type) {
  for (int gridPos = 0; gridPos < grids.size(); gridPos++) {
    for (int rowPos = 0; rowPos < grids[gridPos][type].size(); rowPos++) {
      grids[gridPos][type][rowPos] = grids[gridPos][type][rowPos].findAll{
        it != num
      }

      if (!grids[gridPos][type][rowPos].size()) {
        return [grids, gridPos]
      }
    }
  }

  return [grids, -1]
}

def callNumber(num, grids) {
  gridPos = -1
  (grids, gridPos) = remove(num, grids, 'rows')

  if (gridPos > -1) {
    return [grids, gridPos, 'rows']
  }

  (grids, gridPos) = remove(num, grids, 'cols')

  if (gridPos > -1) {
    return [grids, gridPos, 'cols']
  }

  return [grids, -1, '']
}

def score() {
  def (numbers, grids) = loadInput()

  int gridPos = -1
  int lastNumber = -1
  String type = ''

  // Figure out which grid matches
  for (int i = 0; i < numbers.size(); i++) {
    (grids, gridPos, type) = callNumber(numbers[i], grids)

    if (gridPos > -1) {
      lastNumber = numbers[i] as int
      break
    }
  }

  int remaining = 0

  // Sum the remaining values
  grids[gridPos][type].each{ row ->
    row.each{
      remaining += it as int
    }
  }

  return remaining * lastNumber
}

println score()
