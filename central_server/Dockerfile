FROM python:3.11.4
LABEL authors="Tass"

COPY  requirements.txt server/
RUN python3 -m pip install -r server/requirements.txt


COPY  /src/main/auth /server/auth
COPY  /src/main/db /server/db
COPY  /src/main/stock /server/stock
COPY  /src/main/object /server/preload_data
COPY  /logs /server/logs
COPY  /preload_data /server/preload_data
COPY  local.env /server
COPY  src/main/log_config.py /server
COPY  src/main/main.py /server

WORKDIR server/

#RUN sudo apt install snapd
#RUN sudo snap install --classic certbot

CMD python3 main.py
#CMD python3 -m uvicorn main:app --reload --host=0.0.0.0 --log-config=/server/log.json


#EXPOSE 8000

#ENTRYPOINT ["top", "-b"]