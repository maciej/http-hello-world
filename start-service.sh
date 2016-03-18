#!/usr/bin/env bash

kubectl run hello-node \
    --image=gcr.io/$PROJECT_ID/http-hello-world:1.0 \
    --port=8080

kubectl expose rc hello-node --type="LoadBalancer"

cat << EOM
Run \`kubectl get services hello-node\`
to get the external service IP
EOM
