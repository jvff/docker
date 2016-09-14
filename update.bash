#!/bin/bash

if [ -z "$1" ]; then
	echo "Usage: ./update <docker-image>" >&2
	exit 1
fi

REMAINING_IMAGES="$(mktemp)"

echo "$1" | sed -e 's|/$||' > "$REMAINING_IMAGES"

while [ "$(wc -l "$REMAINING_IMAGES" | cut -f1 -d' ')" -gt 0 ]; do
	IMAGE="$(head -n 1 "$REMAINING_IMAGES")"

	sed -i -e '1d' "$REMAINING_IMAGES"

	echo
	echo
	echo "$IMAGE"
	echo

	docker build -t "$IMAGE" "$IMAGE"

	if [ "$?" -ne 0 ]; then
		echo "Failed to build $IMAGE" >&2
		exit 1
	fi

	grep "^FROM $IMAGE\$" */*/Dockerfile | sed -e "s|/Dockerfile:FROM $IMAGE\$||" >> "$REMAINING_IMAGES"
done

rm -f "$REMAINING_IMAGES"
