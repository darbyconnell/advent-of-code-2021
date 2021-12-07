def loadInput() {
  def converted = []
  new File('input').text.trim().split(',').each{
    converted << (it as int)
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
  Map<String,int> grouped = [:]
  for (int i = 0; i < positions.size(); i++) {
    try {
      grouped[positions[i]]++
    }
    catch (exc) {
      grouped[positions[i]] = 1
    }
  }

  int target = -1
  int lowest = -1

  grouped = grouped.sort{ -it.value }
  def keys = grouped.keySet()
  grouped.keySet().each{ outer ->
    long fuelCost = 0
    grouped.keySet().each{ inner ->
      target = outer

      if (inner != outer) {
        fuelCost += Math.abs(inner - target) * grouped[inner]
      }
    }

    if (fuelCost < lowest || lowest < 0) {
      lowest = fuelCost
    }
  }

  return [
    "Median: " + median(positions),
    "Average: " + average(positions),
    // "Grouped: " + grouped,
    // "Target: " + target,
    // "Fuel Cost: " + fuelCost,
    "Lowest: " + lowest,
  ]
}

println fuelCosts()
