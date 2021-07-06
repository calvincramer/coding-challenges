/// TODO
/// * pre computing primes, prime factorizations, totients, etc (make generalized)
// Internal modules to publish (pub use module::* will export their stuff into the root crate)
pub mod etc;
pub use etc::*;

pub mod fraction;
pub use fraction::*;

pub mod gcf;
pub use gcf::*;

pub mod io;
pub use io::*;

pub mod numeric;
pub use numeric::*;

pub mod palindrome;
pub use palindrome::*;

pub mod prime;
pub use prime::*;

pub mod reversible;
pub use reversible::*;
