#!/bin/sh

trap ctrl_c INT
function ctrl_c() {
    echo "Ctrl + C happened"
    exit
}

HOSTS='192.168.96.201 192.168.96.202 192.168.96.203'

while [ 1 = 1 ]; do
  for v in $HOSTS; do
    echo "$(date +"%T") Go down host $v"
    ssh root@$v 'ip link set ens192 down && sleep 30s && ip link set ens192 up'
    echo "$(date +"%T") Go up host $v "
    sleep 20s
    #sleep 1m
   # exit
  done
  echo "Wait for next cycle 10s"
  sleep 10s
done