#!/bin/bash

docker create --name temp_antminer_build -v /project ubuntu:16.04
docker run --rm --volumes-from temp_antminer_build janitovff/vim_env git clone https://github.com/ckolivas/cgminer /project
docker run --rm --volumes-from temp_antminer_build -w /project janitovff/vim_env git checkout v4.9.2
docker cp icarus_timing.patch temp_antminer_build:/project/
docker run --rm --volumes-from temp_antminer_build -w /project janitovff/vim_env git apply icarus_timing.patch
docker run --rm --volumes-from temp_antminer_build -w /project busybox rm icarus_timing.patch
docker cp build_cgminer.bash temp_antminer_build:/project/
docker run --rm --volumes-from temp_antminer_build -w /project ubuntu:16.04 ./build_cgminer.bash
docker run --rm --volumes-from temp_antminer_build -v $(pwd):/external -w /project busybox tar -czvf /external/cgminer.tar.gz cgminer
docker rm temp_antminer_build

mv cgminer.tar.gz ../
