fn main() {
    for a in 1i64..1000 {
        for b in a+1..1000 {
            let c : i64 = 1000 - a - b;   // Only one option for c
            if a.pow(2) + b.pow(2) == c.pow(2) {
                println!("a={} b={} c={}", a, b, c);
                println!("a+b+c={}", a+b+c);
                println!("abc={}", a*b*c);
                // Supposedly there is one one of this triplet, but continue on just in case
            }
        }
    }
}
// Answer: 31875000
