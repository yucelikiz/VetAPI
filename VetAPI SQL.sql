PGDMP     	    $                {            vet    15.5    15.5 5    3           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            4           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            5           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            6           1262    18229    vet    DATABASE     x   CREATE DATABASE vet WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Turkish_T�rkiye.1254';
    DROP DATABASE vet;
                postgres    false            �            1259    18329    animal    TABLE       CREATE TABLE public.animal (
    id bigint NOT NULL,
    birth_date date,
    breed character varying(255),
    color character varying(255),
    gender character varying(255),
    name character varying(255),
    species character varying(255),
    customer_id bigint
);
    DROP TABLE public.animal;
       public         heap    postgres    false            �            1259    18328    animal_id_seq    SEQUENCE     v   CREATE SEQUENCE public.animal_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.animal_id_seq;
       public          postgres    false    216            7           0    0    animal_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.animal_id_seq OWNED BY public.animal.id;
          public          postgres    false    215            �            1259    18235 
   animal_seq    SEQUENCE     t   CREATE SEQUENCE public.animal_seq
    START WITH 1
    INCREMENT BY 50
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 !   DROP SEQUENCE public.animal_seq;
       public          postgres    false            �            1259    18338    appointment    TABLE     �   CREATE TABLE public.appointment (
    id bigint NOT NULL,
    appointment_date date,
    animal_id bigint,
    doctor_id bigint,
    appointment_time_end time(6) without time zone,
    appointment_time_start time(6) without time zone
);
    DROP TABLE public.appointment;
       public         heap    postgres    false            �            1259    18337    appointment_id_seq    SEQUENCE     {   CREATE SEQUENCE public.appointment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.appointment_id_seq;
       public          postgres    false    218            8           0    0    appointment_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.appointment_id_seq OWNED BY public.appointment.id;
          public          postgres    false    217            �            1259    18405    available_date    TABLE     n   CREATE TABLE public.available_date (
    id bigint NOT NULL,
    available_date date,
    doctor_id bigint
);
 "   DROP TABLE public.available_date;
       public         heap    postgres    false            �            1259    18404    available_date_id_seq    SEQUENCE     ~   CREATE SEQUENCE public.available_date_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.available_date_id_seq;
       public          postgres    false    226            9           0    0    available_date_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.available_date_id_seq OWNED BY public.available_date.id;
          public          postgres    false    225            �            1259    18352    customer    TABLE     �   CREATE TABLE public.customer (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone character varying(255)
);
    DROP TABLE public.customer;
       public         heap    postgres    false            �            1259    18351    customer_id_seq    SEQUENCE     x   CREATE SEQUENCE public.customer_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.customer_id_seq;
       public          postgres    false    220            :           0    0    customer_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.customer_id_seq OWNED BY public.customer.id;
          public          postgres    false    219            �            1259    18361    doctor    TABLE     �   CREATE TABLE public.doctor (
    id bigint NOT NULL,
    address character varying(255),
    city character varying(255),
    mail character varying(255),
    name character varying(255),
    phone character varying(255)
);
    DROP TABLE public.doctor;
       public         heap    postgres    false            �            1259    18360    doctor_id_seq    SEQUENCE     v   CREATE SEQUENCE public.doctor_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.doctor_id_seq;
       public          postgres    false    222            ;           0    0    doctor_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.doctor_id_seq OWNED BY public.doctor.id;
          public          postgres    false    221            �            1259    18370    vaccine    TABLE     �   CREATE TABLE public.vaccine (
    id bigint NOT NULL,
    code character varying(255),
    name character varying(255),
    protection_end_date date,
    protection_start_date date,
    animal_id bigint
);
    DROP TABLE public.vaccine;
       public         heap    postgres    false            �            1259    18369    vaccine_id_seq    SEQUENCE     w   CREATE SEQUENCE public.vaccine_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.vaccine_id_seq;
       public          postgres    false    224            <           0    0    vaccine_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.vaccine_id_seq OWNED BY public.vaccine.id;
          public          postgres    false    223                       2604    18332 	   animal id    DEFAULT     f   ALTER TABLE ONLY public.animal ALTER COLUMN id SET DEFAULT nextval('public.animal_id_seq'::regclass);
 8   ALTER TABLE public.animal ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215    216            �           2604    18341    appointment id    DEFAULT     p   ALTER TABLE ONLY public.appointment ALTER COLUMN id SET DEFAULT nextval('public.appointment_id_seq'::regclass);
 =   ALTER TABLE public.appointment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    217    218    218            �           2604    18408    available_date id    DEFAULT     v   ALTER TABLE ONLY public.available_date ALTER COLUMN id SET DEFAULT nextval('public.available_date_id_seq'::regclass);
 @   ALTER TABLE public.available_date ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225    226            �           2604    18355    customer id    DEFAULT     j   ALTER TABLE ONLY public.customer ALTER COLUMN id SET DEFAULT nextval('public.customer_id_seq'::regclass);
 :   ALTER TABLE public.customer ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219    220            �           2604    18364 	   doctor id    DEFAULT     f   ALTER TABLE ONLY public.doctor ALTER COLUMN id SET DEFAULT nextval('public.doctor_id_seq'::regclass);
 8   ALTER TABLE public.doctor ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221    222            �           2604    18373 
   vaccine id    DEFAULT     h   ALTER TABLE ONLY public.vaccine ALTER COLUMN id SET DEFAULT nextval('public.vaccine_id_seq'::regclass);
 9   ALTER TABLE public.vaccine ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    223    224    224            &          0    18329    animal 
   TABLE DATA           b   COPY public.animal (id, birth_date, breed, color, gender, name, species, customer_id) FROM stdin;
    public          postgres    false    216   ^;       (          0    18338    appointment 
   TABLE DATA              COPY public.appointment (id, appointment_date, animal_id, doctor_id, appointment_time_end, appointment_time_start) FROM stdin;
    public          postgres    false    218   e<       0          0    18405    available_date 
   TABLE DATA           G   COPY public.available_date (id, available_date, doctor_id) FROM stdin;
    public          postgres    false    226   �<       *          0    18352    customer 
   TABLE DATA           H   COPY public.customer (id, address, city, mail, name, phone) FROM stdin;
    public          postgres    false    220   .=       ,          0    18361    doctor 
   TABLE DATA           F   COPY public.doctor (id, address, city, mail, name, phone) FROM stdin;
    public          postgres    false    222   =>       .          0    18370    vaccine 
   TABLE DATA           h   COPY public.vaccine (id, code, name, protection_end_date, protection_start_date, animal_id) FROM stdin;
    public          postgres    false    224   5?       =           0    0    animal_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.animal_id_seq', 12, true);
          public          postgres    false    215            >           0    0 
   animal_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.animal_seq', 51, true);
          public          postgres    false    214            ?           0    0    appointment_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.appointment_id_seq', 15, true);
          public          postgres    false    217            @           0    0    available_date_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.available_date_id_seq', 11, true);
          public          postgres    false    225            A           0    0    customer_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.customer_id_seq', 6, true);
          public          postgres    false    219            B           0    0    doctor_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.doctor_id_seq', 5, true);
          public          postgres    false    221            C           0    0    vaccine_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.vaccine_id_seq', 8, true);
          public          postgres    false    223            �           2606    18336    animal animal_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT animal_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.animal DROP CONSTRAINT animal_pkey;
       public            postgres    false    216            �           2606    18343    appointment appointment_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT appointment_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.appointment DROP CONSTRAINT appointment_pkey;
       public            postgres    false    218            �           2606    18410 "   available_date available_date_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT available_date_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.available_date DROP CONSTRAINT available_date_pkey;
       public            postgres    false    226            �           2606    18359    customer customer_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.customer
    ADD CONSTRAINT customer_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.customer DROP CONSTRAINT customer_pkey;
       public            postgres    false    220            �           2606    18368    doctor doctor_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.doctor
    ADD CONSTRAINT doctor_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.doctor DROP CONSTRAINT doctor_pkey;
       public            postgres    false    222            �           2606    18377    vaccine vaccine_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT vaccine_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT vaccine_pkey;
       public            postgres    false    224            �           2606    18383 '   appointment fk2kkeptdxfuextg5ch7xp3ytie    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fk2kkeptdxfuextg5ch7xp3ytie;
       public          postgres    false    3206    216    218            �           2606    18378 "   animal fk6pvxm5gfjqxclb651be9unswe    FK CONSTRAINT     �   ALTER TABLE ONLY public.animal
    ADD CONSTRAINT fk6pvxm5gfjqxclb651be9unswe FOREIGN KEY (customer_id) REFERENCES public.customer(id);
 L   ALTER TABLE ONLY public.animal DROP CONSTRAINT fk6pvxm5gfjqxclb651be9unswe;
       public          postgres    false    220    3210    216            �           2606    18411 *   available_date fkk0d6pu1wxarsoou0x2e1cc2on    FK CONSTRAINT     �   ALTER TABLE ONLY public.available_date
    ADD CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 T   ALTER TABLE ONLY public.available_date DROP CONSTRAINT fkk0d6pu1wxarsoou0x2e1cc2on;
       public          postgres    false    226    3212    222            �           2606    18398 #   vaccine fkne3kmh8y5pcyxwl4u2w9prw6j    FK CONSTRAINT     �   ALTER TABLE ONLY public.vaccine
    ADD CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j FOREIGN KEY (animal_id) REFERENCES public.animal(id);
 M   ALTER TABLE ONLY public.vaccine DROP CONSTRAINT fkne3kmh8y5pcyxwl4u2w9prw6j;
       public          postgres    false    224    3206    216            �           2606    18388 '   appointment fkoeb98n82eph1dx43v3y2bcmsl    FK CONSTRAINT     �   ALTER TABLE ONLY public.appointment
    ADD CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl FOREIGN KEY (doctor_id) REFERENCES public.doctor(id);
 Q   ALTER TABLE ONLY public.appointment DROP CONSTRAINT fkoeb98n82eph1dx43v3y2bcmsl;
       public          postgres    false    3212    222    218            &   �   x�}��N�0���S���lۑ���6!.����FM�*逾=i7qB�l���߶��q��Cq$� �`�q�%�x[��N�����^�;er{����NV	�k�$�Fu�U�I�ј�b��U��|e��<�6��נ�Fxw��_6ײ�!�z��	┭�����*We{��&L��g��>mzReiX�o�d&��\�6�n�so��Mi���ؤ{$��n���_��ك��� I�@��kt      (   d   x�u�A� ���6�Z�o���Q�<�	&�V(k:DmP!��m <����)Ӭ���0����>}�d7�M���v��(;*��=�������D��+�      0   E   x�M̱� �X�^B����L�;�s2�Mޔ&���ɢ�uϑ�8,Xwj��%��[HwL��sl�      *   �   x�}�AO� ��ǯ�@,-����]�ScO&^h�eE��m�_/�z�E.������`
�q��6�C!K��?h��;���!��	�-~i�9 ％M�i�r�R�L�,�W� U���8L�����8S�o�����݊����/9(�X])V�"'����G��
�5�a4�~���Yӗ�Ȳ�	!X)!ݚ����Oc��t�Ɓ�+j����fUU��5EĢt��[�]g&���6R%z�~G��\n�'�� ��|�      ,   �   x�m��N� @ח��hZ(S�9���Mt��Ģ<Ŏ�{A7�.��I89���2|���}�Z'��%���!V�3i<ӟʽ[]��}�p_h�FZ�!��5��W|���v��CùI�l��E�F?�]�p��#�F�1�F|g����k蝲�����
�9���P��֝�z�1�E�5��/����0�]y��w&F|� W�/�H5�i��
B"�$u�T!�� ,n^      .   �   x�e�A�@�ϳ��?��3�{�D����S�9,iD��oMA2x����éFғ�D�Mۺ)�Ij��B�yy4Q9w>�<޹w�oQY��b�T�"��8���7�O~mXje�3�@����B���<��i�"��.�� �ƿG�,�Eם�;�     