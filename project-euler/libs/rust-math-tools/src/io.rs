use std::fs::File;
use std::io::BufRead;
use std::io::BufReader;

/// Opens and reads a file specified by path. Panics if file doesn't exist
pub fn read_all_lines(path: String) -> Vec<String> {
    let f = File::open(path);
    match f {
        Err(e) => panic!("File doesn't exist: {}", e),
        Ok(file) => BufReader::new(file).lines().map(Result::unwrap).collect(),
    }
}
