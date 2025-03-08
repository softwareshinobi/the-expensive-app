## front end

    valorant-digital-workspace-auth:

        container_name: valorant-digital-workspace-auth

        image: quay.io/dtan4/nginx-basic-auth-proxy:latest

        environment:

            VIRTUAL_HOST: mateomontenegro.online

            LETSENCRYPT_HOST: mateomontenegro.online

            BASIC_AUTH_USERNAME: mateo

            BASIC_AUTH_PASSWORD: valkyrieyprima

            PROXY_PASS: http://valorant-digital-workspace-web/

