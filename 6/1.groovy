def loadInput() {
  def data = new File('input').text.trim().split(',')

  def grouped = [:]
  for (int i = 0; i < data.size(); i++) { 
    try { grouped[data[i]]++ } catch (exc) { grouped[data[i]] = 1 }
  }

  return grouped
}

def nextDay(counts) {
  def keys = (counts.keySet() as List).sort{ (it as int) }

  int newFish = 0

  for (int i = 0; i < keys.size(); i++) {
    int key = keys[i] as int
    String sKey = key as String
    if (key == 0 && counts[sKey]) {
      newFish += counts[sKey]
      counts[sKey] = 0
    }
    else if (key > 0){
      String nKey = (key - 1) as String
      counts[nKey] = counts[sKey]
      counts[sKey] = 0
    }
  }

  counts['8'] = newFish
  try { counts['6'] += newFish } catch (exc) { counts['6'] = newFish }

  return counts
}

def lanternFish(days) {
  def counts = loadInput()

  for (int i = 0; i < days; i++) {
    counts = nextDay(counts)
  }

  int total = 0
  def keys = (counts.keySet() as List)

  for (int i = 0; i < keys.size(); i++) {
    total += counts[(keys[i] as String)]
  }
  return total
}

println lanternFish(80)
