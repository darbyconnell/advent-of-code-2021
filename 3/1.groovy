def powerConsumption() {
  def positionZero = []
  def positionOne = []

  new File('input').eachLine { line ->
    for (int i = 0; i < line.size(); i++) {
      if (line[i] == '0') {
        try { positionZero[i]++ } catch (exc) { positionZero[i] = 1 }
      }
      else {
        try { positionOne[i]++ } catch (exc) { positionOne[i] = 1 }
      }
    }
  }

  String gamma = ''
  String epsilon = ''

  for (int i = 0; i < positionZero.size(); i++) {
    gamma += positionZero[i] > positionOne[i] ? '0' : '1';
    epsilon += positionZero[i] > positionOne[i] ? '1' : '0';
  }

  return Integer.parseInt(gamma, 2) * Integer.parseInt(epsilon, 2)
}

println powerConsumption()
