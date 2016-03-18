#!/usr/bin/env bash

# Cluster configuration variables

# Run
# gcloud compute machine-types list --zone="$GCLOUD_ZONE"
# to see list of available machine types
MACHINE_TYPE="g1-small"

NUM_NODES="3"

gcloud container clusters create $GCLOUD_CLUSTER_NAME \
    --machine-type=$MACHINE_TYPE \
    --num-nodes=$NUM_NODES

gcloud container clusters get-credentials $GCLOUD_CLUSTER_NAME
