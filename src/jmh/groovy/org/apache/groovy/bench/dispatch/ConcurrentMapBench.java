/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.groovy.bench.dispatch;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.codehaus.groovy.util.*;


@Warmup(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(3)
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class ConcurrentMapBench {
    @Benchmark
    public void testManagedIdentityConcurrentMap_put(ManagedIdentityConcurrentMapState state, Blackhole bh) {
        ManagedIdentityConcurrentMap m = state.m;
		
		for (int i = 100; i < 200; i++) {
			m.put(i, i);
		}
    }
	
	@Benchmark
    public void testManagedIdentityConcurrentMap_get(ManagedIdentityConcurrentMapState state, Blackhole bh) {
        ManagedIdentityConcurrentMap m = state.m;
		
		for (int i = 0; i < 200; i++) {
			m.get(i);
		}
    }
	
	@Benchmark
    public void testManagedConcurrentMap_put(ManagedConcurrentMapState state, Blackhole bh) {
        ManagedConcurrentMap m = state.m;
		
		for (int i = 100; i < 200; i++) {
			m.put(i, i);
		}
    }
	
	@Benchmark
    public void testManagedConcurrentMap_get(ManagedConcurrentMapState state, Blackhole bh) {
        ManagedConcurrentMap m = state.m;
		
		for (int i = 0; i < 200; i++) {
			m.get(i);
		}
    }
	
	@Benchmark
    public void testConcurrentIdentityHashMap_put(ConcurrentIdentityHashMapState state, Blackhole bh) {
        ConcurrentIdentityHashMap m = state.m;
		
		for (int i = 100; i < 200; i++) {
			m.put(i, i);
		}
    }
	
	@Benchmark
    public void testConcurrentIdentityHashMap_get(ConcurrentIdentityHashMapState state, Blackhole bh) {
        ConcurrentIdentityHashMap m = state.m;
		
		for (int i = 0; i < 200; i++) {
			m.get(i);
		}
    }
	
	@Benchmark
    public void testConcurrentReferenceHashMap_put(ConcurrentReferenceHashMapState state, Blackhole bh) {
        ConcurrentReferenceHashMap m = state.m;
		
		for (int i = 100; i < 200; i++) {
			m.put(i, i);
		}
    }
	
	@Benchmark
    public void testConcurrentReferenceHashMap_get(ConcurrentReferenceHashMapState state, Blackhole bh) {
        ConcurrentReferenceHashMap m = state.m;
		
		for (int i = 0; i < 200; i++) {
			m.get(i);
		}
    }
	
    @State(Scope.Thread)
    public static class ManagedIdentityConcurrentMapState {
        ManagedIdentityConcurrentMap m;
		
        @Setup(Level.Trial)
        public void setUp() {
            m = new ManagedIdentityConcurrentMap(ReferenceBundle.getHardBundle());
			
			for (int i = 0; i < 150; i++) {
				m.put(i, i);
			}
        }
    }
	
	@State(Scope.Thread)
    public static class ManagedConcurrentMapState {
        ManagedConcurrentMap m;
		
        @Setup(Level.Trial)
        public void setUp() {
            m = new ManagedConcurrentMap(ReferenceBundle.getHardBundle());
			
			for (int i = 0; i < 150; i++) {
				m.put(i, i);
			}
        }
    }
	
	@State(Scope.Thread)
    public static class ConcurrentIdentityHashMapState {
        ConcurrentIdentityHashMap m;
		
        @Setup(Level.Trial)
        public void setUp() {
            m = new ConcurrentIdentityHashMap();
			
			for (int i = 0; i < 150; i++) {
				m.put(i, i);
			}
        }
    }
	
	@State(Scope.Thread)
    public static class ConcurrentReferenceHashMapState {
        ConcurrentReferenceHashMap m;
		
        @Setup(Level.Trial)
        public void setUp() {
            m = new ConcurrentReferenceHashMap();
			
			for (int i = 0; i < 150; i++) {
				m.put(i, i);
			}
        }
    }
}


