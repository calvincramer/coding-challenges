fn main() {
    let mut ways = 0;
    for tp1 in (0..=200).step_by(1) {
        //one pence coin
        for tp2 in (0..=200).step_by(2) {
            //two pence coin
            if tp1 + tp2 > 200 {
                break;
            }
            for tp5 in (0..=200).step_by(5) {
                //five pence coin
                if tp1 + tp2 + tp5 > 200 {
                    break;
                }
                for tp10 in (0..=200).step_by(10) {
                    //ten pence coin
                    if tp1 + tp2 + tp5 + tp10 > 200 {
                        break;
                    }
                    for tp20 in (0..=200).step_by(20) {
                        //twenty pence coin
                        if tp1 + tp2 + tp5 + tp10 + tp20 > 200 {
                            break;
                        }
                        for tp50 in (0..=200).step_by(50) {
                            //fifty pence coin
                            if tp1 + tp2 + tp5 + tp10 + tp20 + tp50 > 200 {
                                break;
                            }
                            for tp100 in (0..=200).step_by(100) {
                                //one pound coin
                                if tp1 + tp2 + tp5 + tp10 + tp20 + tp50 + tp100 > 200 {
                                    break;
                                }
                                for tp200 in (0..=200).step_by(200) {
                                    //two pound coin
                                    if tp1 + tp2 + tp5 + tp10 + tp20 + tp50 + tp100 + tp200 == 200 {
                                        println!(
                                            "{} {} {} {} {} {} {} {}",
                                            tp1, tp2, tp5, tp10, tp20, tp50, tp100, tp200
                                        );
                                        ways += 1;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    println!("Total ways: {}", ways);
}
