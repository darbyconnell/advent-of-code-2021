def loadInput() {
  def converted = []
  new File('input').text.trim().split(',').each{
    converted << (it as long)
  }
  return converted
}

def median(positions) {
  def middle = positions.size().intdiv(2)

  positions.size() % 2 ? positions[middle] : (positions[middle - 1] + positions[middle]) / 2
}

def average(positions) {
  return positions.sum() / positions.size()
}

def fuelCosts(days) {
  def positions = loadInput()
  Map<String,long> grouped = [:]
  for (long i = 0; i < positions.size(); i++) {
    try {
      grouped[positions[i]]++
    }
    catch (exc) {
      grouped[positions[i]] = 1
    }
  }

  long target = -1
  long lowest = -1
  long lowestTarget = -1

  grouped = grouped.sort{ -it.value }
  def keys = grouped.keySet()
  for (long outer = 0; outer < grouped.keySet().max(); outer++) {
    long fuelCost = 0
    grouped.keySet().each{ inner ->
      target = outer

      if (inner != outer) {
        long diff = Math.abs(inner - target)
        long cost = 0
        for (long i = 1; i <= diff; i++) {
          cost += i
        }
        fuelCost += cost * grouped[inner]
      }
    }

    if (fuelCost < lowest || lowest < 0) {
      lowest = fuelCost
      lowestTarget = target
    }
  }

  return [
    "Median: " + median(positions),
    "Average: " + average(positions),
    "Actual: " + lowestTarget,
    "Fuel Cost: " + lowest,
  ]
}

println fuelCosts()
