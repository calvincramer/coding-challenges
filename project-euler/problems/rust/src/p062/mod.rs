use itertools::Itertools;
use num_integer::Roots;

struct Obj {
    low_perm: String,
    cubes: Vec<String>,
}

fn get_obj<'a>(perm: &String, objs: &'a mut Vec<Obj>) -> Option<&'a mut Obj> {
    for obj in objs {
        if obj.low_perm == *perm {
            return Some(obj);
        }
    }
    None
}

pub struct P062 {}
impl crate::Problem for P062 {
    fn run(&self, verbose: bool) -> (i32, String, String) {
        let mut objs = Vec::<Obj>::new();
        let mut n = 0u64;
        const MAX: usize = 5;

        loop {
            n += 1;
            // get smallest permutation of digits by sorting
            let perm = n.pow(3).to_string();
            let low_perm = perm.chars().sorted().collect::<String>();
            let found = get_obj(&low_perm, &mut objs);
            if found.is_none() {
                objs.push(Obj{low_perm: low_perm, cubes: vec![perm]});
                continue;
            }
            let obj = found.unwrap();
            obj.cubes.push(perm);
            if obj.cubes.len() == MAX {
                if verbose {
                    println!("Low perm = {}", obj.low_perm);
                    for i in 0..obj.cubes.len() {
                        println!("\t{} -> {} = {}^3", i, obj.cubes[i], obj.cubes[i].parse::<u64>().unwrap().nth_root(3));
                    }
                }
                return (62, "Smallest cube".to_string(), obj.cubes[0].to_string())
            }
        }
        // 127035954683
    }
}
