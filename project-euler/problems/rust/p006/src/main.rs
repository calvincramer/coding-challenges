fn main() {
    let sum_sq : i64 = (1..=100).sum::<i64>().pow(2);
    let sq_sum : i64 = (1..=100).map(|x:i64| x.pow(2)).sum();
    println!("{}", sum_sq - sq_sum)
}
// Answer: 25164150
