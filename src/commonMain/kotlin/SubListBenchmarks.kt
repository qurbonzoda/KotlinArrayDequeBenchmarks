package bench

import kotlinx.benchmark.*

@State(Scope.Benchmark)
@Measurement(iterations = 7, time = 500, timeUnit = BenchmarkTimeUnit.MILLISECONDS)
@Warmup(iterations = 7, time = 500, timeUnit = BenchmarkTimeUnit.MILLISECONDS)
@OutputTimeUnit(BenchmarkTimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
open class SubListBenchmarks {
    private var deque = ArrayDeque<String>()

    @Param("100", "1000", "10000")
    var size: Int = 0

    @Benchmark
    fun clear(): Int {
        deque = ArrayDeque()
        for (i in 0..<size) {
            deque.add(i.toString())
        }

        val quarter = size / 4
        deque.subList(quarter, size - quarter).clear()

        return deque.size
    }
}