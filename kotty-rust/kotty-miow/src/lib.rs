/*
 * Copyright 2025 0x746143
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
mod simple_tcp_server;

use crate::simple_tcp_server::start_simple_tcp_server;

#[unsafe(no_mangle)]
pub extern "C" fn kotty_miow_init_server() -> i32 {
    println!("Initializing server...");
    match start_simple_tcp_server() {
        Ok(()) => 0,
        Err(_) => -1,
    }
}
