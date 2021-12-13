def loadInput() {
  def connections = [:]
  new File('input').eachLine {
    def pieces = it.trim().split('-')
    try { connections[pieces[0]] << pieces[1] } catch (exc) { connections[pieces[0]] = [pieces[1]] }
    if (pieces[0] != 'start' && pieces[1] != 'end') {
      try { 
        connections[pieces[1]] << pieces[0]
      } catch (exc) {
        connections[pieces[1]] = [pieces[0]]
      }
    }
  }

  return connections
}

// Global variables without the def
paths = []
connections = loadInput()
count = 0

def followPath(cave, currentPath = '', visited = []) {
  def cloneVisited = visited.clone()
  if (cave != 'start' && cave != 'end' && cave == cave.toLowerCase()) {
    cloneVisited << cave
  }

  if (currentPath != '') currentPath += "|"
  currentPath += cave

  paths << currentPath

  if (cave == 'end') return

  for (int i = 0; i < connections[cave].size(); i++) {
    if (!cloneVisited.contains(connections[cave][i])) {
      followPath(connections[cave][i], currentPath, cloneVisited)
    }
  }
}

def pathCount() {
  followPath('start')
  return paths.findAll{
    def pieces = it.split('\\|')
    return pieces[0] == 'start' && pieces[-1] == 'end'
  }.size()
}

println pathCount()
