#!/bin/bash

if [ -z "$1" ]; then
	echo "Please select private key file"
	keys_file="$(mktemp)"
	ls ssh_keys | sed -e '/\.pub$/d' > "$keys_file"

	cat -n "$keys_file"

	read option

	number="$(echo "$option" | sed -e 's/[^0-9]//g')"

	if [ -z "$number" ]; then
		echo "Invalid key option" >&2
		rm -f "$keys_file"
		exit 1
	fi

	key_name="$(sed -ne "$number p" "$keys_file")"
	key_file="ssh_keys/$key_name"

	rm -f "$keys_file"
else
	key_file="$1"
fi

if [ ! -f "$key_file" ]; then
	echo "Invalid key file" >&2
	exit 2
fi

echo "Key: $key_file"

if [ -z "$2" ]; then
	echo "Please select container"
	containers_file="$(mktemp)"
	docker ps --format {{.Names}} > "$containers_file"

	cat -n "$containers_file"

	read option

	number="$(echo "$option" | sed -e 's/[^0-9]//g')"

	if [ -z "$number" ]; then
		echo "Invalid container option" >&4
		rm -f "$containers_file"
		exit 1
	fi

	container="$(sed -ne "$number p" "$containers_file")"

	rm -f "$containers_file"
else
	container="$2"
fi

echo "Container: $container"

docker cp "$key_file" "${container}:/root/.ssh/"
docker exec -it "$container" ssh-keygen -f "/root/.ssh/$(basename "$key_file")" -p
