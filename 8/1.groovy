def loadInput() {
  def patterns = []
  def output = []
  new File('input').eachLine {
    def pieces = it.split(' \\| ')
    patterns << pieces[0]
    output << pieces[1]
  }
  return [patterns, output]
}

def readout() {
  def patterns = []
  def output = []
  (patterns, output) = loadInput()

  Map<long,long> counter = [:]
  long total = 0

  output.each{ out ->
    out.split(' ').each {
      switch(it.size()) {
        case '3':
          try { counter[7]++ } catch (exc) { counter[7] = 1 }
          total++
          break
        case '4':
          try { counter[4]++ } catch (exc) { counter[4] = 1 }
          total++
          break
        case '2':
          try { counter[1]++ } catch (exc) { counter[1] = 1 }
          total++
          break
        case '7':
          try { counter[8]++ } catch (exc) { counter[8] = 1 }
          total++
          break
      }
    }
  }

  return [total, counter]
}

println readout()
