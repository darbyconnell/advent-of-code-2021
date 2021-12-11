def loadInput() {
  def output = []
  new File('input').eachLine {
    output << it.trim().split("(?!^)")
  }
  return output
}

def flash(y, x, energyLevels) {
  if (y < 0 || x < 0 || y > energyLevels.size() - 1 || x > energyLevels[y].size() - 1) return energyLevels

  int current
  try {
    current = (energyLevels[y][x] as int) + 1
  } catch (exc) {
    return energyLevels
  }

  if (current > 10) return energyLevels

  energyLevels[y][x] = current as String

  // Increment all of the neighbors if we are at 10
  if (current == 10) {
    energyLevels = flash(y-1, x-1, energyLevels)
    energyLevels = flash(y-1, x, energyLevels)
    energyLevels = flash(y-1, x+1, energyLevels)
    energyLevels = flash(y, x-1, energyLevels)
    energyLevels = flash(y, x+1, energyLevels)
    energyLevels = flash(y+1, x-1, energyLevels)
    energyLevels = flash(y+1, x, energyLevels)
    energyLevels = flash(y+1, x+1, energyLevels)
  }

  return energyLevels
}

def flashCount(steps) {
  energyLevels = loadInput()
  int octopuses = energyLevels[0].size() * energyLevels.size()

  for (int i = 0; i < steps; i++) {
    long flashes = 0
    // Increase everyone including the neighbors
    for (int y = 0; y < energyLevels.size(); y++) {
      for (int x = 0; x < energyLevels[y].size(); x++) {
        energyLevels = flash(y, x, energyLevels)
      }
    }

    // Mark flashes and reset counts for those over 9
    for (int y = 0; y < energyLevels.size(); y++) {
      for (int x = 0; x < energyLevels[y].size(); x++) {
        int current = energyLevels[y][x] as int
        if (current > 9) {
          flashes++
          energyLevels[y][x] = 0
        }
      }
    }

    if (flashes == octopuses) return i + 1
  }

  return -1
}

println flashCount(10000)
